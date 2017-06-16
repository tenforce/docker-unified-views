#!/bin/bash
########################################################################
# Builder for all the known UV components system according to docker setup. 
#
# Environment variables which can be set are:
#
#   DOWNLOAD_ALLOWED ("yes", "no")
#
#   DB_CONNECTION ("mysql","mssql")
#
#   STAGE ("DOWNLOAD_BUILD", "SAVE_BUILT", "INSTALL_BUILT")
#
set -x

INSTALLDIR=`pwd`

########################################################################
# Defines the stages of this process
#
# - download/build (DOWNLOAD_BUILD)
# - save the built components (SAVE_BUILT}
# - and the install what has been built stage (INSTALL_BUILT).
#
# The default is DOWNLOAD_BUILD which will download and build the
# software. Some software will need to be present for both the
# building (java) and the installation.

: ${STAGE:="DOWNLOAD_BUILD"} 
#: ${STAGE:="SAVE_BUILT"} 
    
########################################################################
# Environment variables which have basic defaults.

: ${DOWNLOAD_ALLOWED:="no"}
: ${DB_CONNECTION:="mysql"}
: ${MAVEN_OPTS:="-Xms256m -Xmx2048m "}
#: ${MAVEN_OPTS:="-Xms256m -Xmx2048m -XX:PermSize=256m"}
: ${BUILDDIR:=uv_build}
: ${ORACLESUPPORT:="no"}
: ${MSSQLSUPPORT:="no"}
: ${DOCKERTARGETDIR:="/home/vagrant/uv/docker-unified-views"}

  # Overwrite with a sesame.version = 2.8.9 (normally 2.8.1)

: ${SESAME_VERSION:=2.8.9}

  ######################################################################
  # STAGE also determines download possibilities

if [ "${STAGE}" != "DOWNLOAD_BUILD" ]
then
    DOWNLOAD_ALLOWED=no
fi

  ######################################################################
  # Account details used throughout the system (change when needed)

: ${UV_DATABASE_SQL_USER:=root}
: ${UV_DATABASE_SQL_PASSWORD:=root}
: ${MASTER_USER:=master}
: ${MASTER_PASSWORD:=commander}

  ######################################################################

export DOWNLOAD_ALLOWED DB_CONNECTION MAVEN_OPTS


########################################################################
# Assume connected to the Internet and that files can be 
# downloaded as needed. 

MAVEN_REPO_LOCAL=${INSTALLDIR}/downloads/repository
MAVEN_OFFLINE=
if [ "${DOWNLOAD_ALLOWED}" = "no" -o "${STAGE}" = "INSTALL_BUILT" ] ; then
    MAVEN_OFFLINE="-o"
fi

########################################################################

UV_VERSION=2.3.0
UV_PLUGINS_VERSION=2.2.1

########################################################################

JAVA_VERSION=1.8.0
MAVEN_VERSION=3.3.9
TOMCAT_VERSION=7.0.67

   # set Maven JAVA profile for the build

if [ "${JAVA_VERSION}" = "1.8.0" ] ; then
#    MAVEN_PROFILE="-P java8"
    MAVEN_PROFILE=
else
    MAVEN_PROFILE=
fi
   
########################################################################

MUSERNAME=$1
MPASSWORD=$2
MSERVER=smtp.gmail.com

########################################################################

check_installed() {
    if ! hash $1 2>/dev/null; then
	echo "ERROR: $1 is not installed"
	exit -1;
    fi
}

test_installed() {
    if ! hash $1 2>/dev/null; then
	echo "Warning: $1 is not installed"
	return 1;
    else 
	return 0;
    fi
}

# XXX add option for systemd
check_service_installed() {
    if [ -e /etc/init.d/$1 ] ; then
	echo "ERROR: $1 is not installed"
	exit -1;
    fi
}

test_service_installed() {
    if [ -e "/etc/init.d/$1" ] ; then
	return 0;
    else 
	return 1;
	echo "Warning: $1 is not installed"
    fi
}

########################################################################
# Download function which will using the -N option make sure that the
# operation is only done once and will be saved in the downloads directory

wget_n() {
    mkdir -p ${INSTALLDIR}/downloads
    pushd ${INSTALLDIR}/downloads
      # Reduce the number of attempts since it will be used off-line
      if ! wget -N -t 2 $1 ;
      then
	  echo "*** Error: failed to download - $1"
	  exit -1;
      fi
    popd
}

########################################################################
# install function for the set of default tools, editor and browser

install_default() {
    if ! hash git 2>/dev/null; then
	apt-get -y install curl git dos2unix autoconf make wget gawk bison m4 
    fi
}

install_editor() {
    if ! hash emacs 2>/dev/null; then
	apt-get -y install emacs vim
    fi
}

install_webbrowser() {
    if ! hash firefox 2>/dev/null; then
	apt-get -y install firefox
    fi
}


########################################################################

install_java() {
    test_installed java 
    INSTALLED=$?
    if [ ${INSTALLED} -gt 0 ] ;
    then
	case "${JAVA_VERSION}" in
        "1.7.0")
	    apt-get install -y openjdk-7-jdk ;;
        "1.8.0")
	    apt-get install -y openjdk-8-jdk ;;
        "1.9.0")
	    apt-get install -y openjdk-9-jdk ;;
        *)
            echo "no match for the java version - update repository with a known java version" 
            exit -1 ;;
        esac
    fi

    if [ ! -e /etc/environment.bak ] ; 
    then
    cp /etc/environment /etc/environment.bak
    echo "export JAVA_HOME=/usr/lib/jvm/default-java" >> /etc/environment
    echo "export JRE_HOME=/usr/lib/jvm/default-java/jre" >> /etc/environment
    fi
    if [ ! -e "${HOME}/.bashrc.bak" ] ; 
    then
    cp ${HOME}/.bashrc ${HOME}/.bashrc.bak 
    echo "export JAVA_HOME=\"/usr/lib/jvm/default-java\"" >> ${HOME}/.bashrc
    echo "export JRE_HOME=\"/usr/lib/jvm/default-java/jre\"" >> ${HOME}/.bashrc	
    fi
   
   echo "If more than one java is installed, please configure the default to be used"
   echo "use update-alternatives --config java "
        	
#	echo "export JAVA_HOME=/usr/java/jdk1.7.0_80/jre" >> /etc/environment
#	echo "export JRE_HOME=/usr/java/jdk1.7.0_80/jre" >> /etc/environment	
#    elif [ -d "/usr/lib/jvm/java-${JAVA_VERSION}-openjdk.x86_64" ]
#    then
#	echo "*** INFO: java version installed okay"
#    else
#	# Assume an update will be required.
#	apt-get -y install java-${JAVA_VERSION}-openjdk-devel
#	# Add to path setting
#	echo "export JAVA_HOME=\"/usr/lib/jvm/java-${JAVA_VERSION}-openjdk.x86_64\"" >> ${HOME}/.bashrc
#	echo "export JRE_HOME=\"/usr/lib/jvm/java-${JAVA_VERSION}-openjdk.x86_64\"" >> ${HOME}/.bashrc	
#	echo "export JAVA_HOME=/usr/lib/jvm/java-${JAVA_VERSION}-openjdk.x86_64" >> /etc/environment
#	echo "export JRE_HOME=/usr/lib/jvm/java-${JAVA_VERSION}-openjdk.x86_64" >> /etc/environment	
#        if  [ ! -d "/usr/lib/jvm/java-${JAVA_VERSION}-openjdk.x86_64" ]
#	then
#	    echo "*** ERROR: failed to setup Java - download from Oracle"
#	    exit -1;
#	fi
#    fi
#   fi
}

########################################################################
# Install maven3 (version required by UnifiedViews).

download_maven3() {
    mkdir -p ${INSTALLDIR}/downloads
    pushd ${INSTALLDIR}/downloads
    apt-get download -y maven
    popd
}

install_maven3() {
    test_installed mvn
    INSTALLED=$?
    if [ ${INSTALLED} -gt 0 ] ;
    then
      apt-get install -y  maven
    fi
}

########################################################################
# Install Tomcat7 (version required by UnifiedViews).

download_tomcat7() {
    mkdir -p ${INSTALLDIR}/downloads
    pushd ${INSTALLDIR}/downloads
    apt-get download -y tomcat7
    popd
}


install_tomcat7() {
    test_service_installed tomcat7
    INSTALLED=$?
    if [ ! ${INSTALLED} ] ;
    then
      apt-get install -y tomcat7 
    fi
}

#################################################################

install_mysql() {
    test_installed mysql
    INSTALLED=$?
    if [ ! ${INSTALLED} ] 
    then
      apt-get install -y mysql-server
      systemctl start mysql
      mysqladmin --user=root --password=root
      systemctl enable mysql 
      systemctl restart mysql
    fi
    
}

#################################################################
# Download all the required parts for UV.

download_uv() {
    mkdir -p ${INSTALLDIR}/downloads
    pushd ${INSTALLDIR}/downloads
    git clone --depth 1 https://github.com/UnifiedViews/Core.git
    git clone --depth 1 https://github.com/UnifiedViews/Plugins.git
    git clone --depth 1 https://github.com/UnifiedViews/Plugin-DevEnv.git
    mkdir -p dpus
    # additional dpus
    pushd dpus
    git clone --depth 1 https://github.com/tenforce/uv-dpu-t-json-to-rdf && rm -rf uv-dpu-t-json-to-rdf/.git
    git clone --depth 1 https://github.com/tenforce/uv-dpu-e-http-client && rm -rf uv-dpu-e-http-client/.git
    git clone --depth 1 https://github.com/tenforce/uv-dpu-l-sparqlToVirtuoso && rm -rf uv-dpu-l-sparqlToVirtuoso/.git
    git clone --depth 1 https://github.com/bertvannuffelen/uv-dpu-t-sparqlUpdateMultiple && rm -rf uv-dpu-t-sparqlUpdateMultiple/.git
    popd    
    # transfer all dpus 
    cp -r dpus/* Plugins
    # add the dpus to be build in the module list of the Plugin/pom.xml
    # ensure corresponding plugin-environment
    popd
}

#################################################################
# save everything which might be needed for building UV offline.

save_uv_downloads() {
    pushd ${INSTALLDIR}/downloads
    if [ -d "Core-release-UV_Core_v2.3.0" ]
    then
	# Only do this at present for the 2.3.0 version of unified views
	for i in Plugin-DevEnv-master Core-release-UV_Core_v2.3.0 Plugins-release-UV_Plugins_v2.2.1
	do
            pushd $i
   	     mvn dependency:go-offline -Dmaven.repo.local=${MAVEN_REPO_LOCAL}
	    popd
	done
    fi
    popd
}
    
#################################################################
# Note: as far as possible, put everything in standard Linux 
# locations for the sake of the sys-admin. 

install_oracle_jar() {
    pushd ${INSTALLDIR}/downloads
      # The OJDBC7.JAR has to be manually downloaded from oracle site (sorry)
      # DPU required parts downloaded, unpack where needed.
      mvn -Dmaven.repo.local=${MAVEN_REPO_LOCAL} ${MAVEN_OFFLINE} \
          install:install-file -Dfile=${INSTALLDIR}/downloads/ojdbc7.jar \
	  -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.2.0 \
	  -Dpackaging=jar
    popd
}

install_local_virtuoso_jars() {
    pushd ${INSTALLDIR}/downloads
      mvn -Dmaven.repo.local=${MAVEN_REPO_LOCAL} ${MAVEN_OFFLINE} \
          install:install-file -Dfile=${INSTALLDIR}/downloads/virt_sesame2.jar \
	  -DgroupId=virtuoso.sesame2 -DartifactId=driver -Dversion=2.7 \
	  -Dpackaging=jar
      mvn -Dmaven.repo.local=${MAVEN_REPO_LOCAL} ${MAVEN_OFFLINE} \
          install:install-file -Dfile=${INSTALLDIR}/downloads/virtjdbc4.jar \
	  -DgroupId=virtuoso -DartifactId=jdbc-driver -Dversion=4.0 \
	  -Dpackaging=jar
    popd
}

# Add the extra jdbc driver jar (pom for the backend also updated).

install_mssqlserver_jar() {
    pushd ${INSTALLDIR}/downloads
      # The sqljdbc_6.0.6629.101_enu.tar.gz has to be manually
      # downloaded from the microsoft website (and license agreed to).
      if [ "${DB_CONNECTION}" = "mssql" ]
      then
	  mkdir packages/lib
	  if [ ! -f "sqljdbc_6.0.6629.101_enu.tar.gz" ]
	  then
	      echo "*** ERROR: sqljdbc_6.0.6629.101_enu.tar.gz needs to be downloaded"
	      exit -1;
	  fi
	  tar xvf sqljdbc_6.0.6629.101_enu.tar.gz
	  cp sqljdbc_6.0/enu/sqljdbc41.jar packages/lib
	  # YUK (would be much better elsewhere)
	  cp sqljdbc_6.0/enu/sqljdbc41.jar /usr/local/tomcat7/lib
	  chown unifiedviews.unifiedviews /usr/local/tomcat7/lib/sqljdbc41.jar
	  # Also make available for any build operations
	  mvn -Dmaven.repo.local=${MAVEN_REPO_LOCAL} ${MAVEN_OFFLINE} \
              install:install-file -Dfile=${INSTALLDIR}/downloads/sqljdbc_6.0/enu/sqljdbc41.jar \
	      -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc41 -Dversion=4.1 \
	      -Dpackaging=jar
	  # Overwrite the backend pom so that it contains the ms driver jar
	  cp ${INSTALLDIR}/config-files/backend-pom.xml Core*/backend/pom.xml
      else
	  echo "*** INFO: mssql driver noy installed"
      fi
    popd
}

build_uv_plugins() {
    pushd ${INSTALLDIR}/downloads
	 pushd Plugin-DevEnv
	    # cp ${INSTALLDIR}/config-files/dev-pom.xml pom.xml
          mvn -Dsesame.version=${SESAME_VERSION} \
              -DskipTests \
              -Dmaven.javadoc.skip=true \
	      -Dmaven.repo.local=${MAVEN_REPO_LOCAL} ${MAVEN_OFFLINE} ${MAVEN_PROFILE} \
	      install
	 popd
	 pushd Plugins
          mvn -Dsesame.version=${SESAME_VERSION} -DskipTests \
              -Dmaven.javadoc.skip=true \
	      -Dmaven.repo.local=${MAVEN_REPO_LOCAL} ${MAVEN_OFFLINE} ${MAVEN_PROFILE} \
	      package
          mvn -Dsesame.version=${SESAME_VERSION} -DskipTests \
              -Dmaven.javadoc.skip=true \
	      -Dmaven.repo.local=${MAVEN_REPO_LOCAL} ${MAVEN_OFFLINE} ${MAVEN_PROFILE} \
	      package -P debian
	 popd
	 mkdir -p ${INSTALLDIR}/downloads/packages/plugins
         cp ${INSTALLDIR}/downloads/Plugins/debian/target/plugins/* ${INSTALLDIR}/downloads/packages/plugins
    popd
}

build_uv_core() {
    pushd ${INSTALLDIR}/downloads    
     pushd *Core*
      mvn -Dsesame.version=${SESAME_VERSION} \
          -DskipTests \
          -Dmaven.javadoc.skip=true \
	  -Dmaven.repo.local=${MAVEN_REPO_LOCAL} ${MAVEN_OFFLINE} ${MAVEN_PROFILE} \
	  install
     popd
    
     mkdir -p packages/lib
     cp Core*/backend/target/*.jar packages
     cp -r Core*/backend/target/lib packages
     cp Core*/frontend/target/*.war packages
     cp Core*/master/target/*.war packages
     # any extras needed
     cp -r Core*/target/lib packages
    popd
}

copy_uv_plugins() {
    pushd ${INSTALLDIR}/downloads
      # Now copy the plugin jars to where they are supposed to be before installing
      mkdir -p /usr/local/unifiedviews/dpus
      mkdir -p /usr/local/unifiedviews/lib
      mkdir -p /usr/local/tomcat7/lib
      cp jsch-0.1.*.jar /usr/local/unifiedviews/lib
      cp jsch-0.1.*.jar /usr/local/tomcat7/lib	    
      if [ "${UV_PLUGINS_VERSION}" = "2.1.0" ]
      then
	  cp dpus/*.jar /usr/local/unifiedviews/dpus
	  # Update libraries where needed.
	  cp dpu-lib/*.jar /usr/local/tomcat7/lib
	  cp jsch-0.1.49.jar /usr/local/tomcat7/lib 
	  cp jsch-0.1.49.jar /usr/local/unifiedviews/lib
	  cp dpu-lib/*.jar /usr/local/unifiedviews/lib
      else
	  echo "*** INFO: Copying dpu's etc"
	  cp Plugins-release-UV_Plugins_v2.2.1/*/target/*.jar /usr/local/unifiedviews/dpus
      fi
    popd
}

#############################################################################
# Test files are where they should be (not that it works)
check_uv_installation() {
    FILES="/usr/local/tomcat7/webapps/unifiedviews.war /usr/local/tomcat7/webapps/master.war"

    for myfile in ${FILES}
    do
        echo "check ${myfile}"
	if [ ! -f ${myfile} ]
	then
	    echo "$myfile not found"
	    exit -1 ;
	fi
    done
}

##############################################################################
# Creation of the DB tables, etc. 
create_dbtables() {
    if [ "${DB_CONNECTION}" = "mysql" ]
    then
	echo "*** INFO - create mysql database needed"
	echo "create database unifiedviews; use unifiedviews" | mysql -u root -proot
	if [ "${UV_PLUGINS_VERSION}" = "2.1.0" ]
	then
	    mysql -u root -proot unifiedviews < ${INSTALLDIR}/downloads/schema.sql
	    mysql -u root -proot unifiedviews < ${INSTALLDIR}/downloads/data-core.sql
	    mysql -u root -proot unifiedviews < ${INSTALLDIR}/downloads/data-permissions.sql
	else
	    echo "*** INFO - db schema will be created by backend"
	fi
    else
	echo "*** INFO: tables not created yet - only for mysql/2.1.0"
    fi
}

###############################################################################

: ${UV_USER:=unifiedviews}

create_uv_user() {
       useradd ${UV_USER}
       groupadd ${UV_USER}
       useradd -s /bin/bash -g ${UV_USER} ${UV_USER}
}

create_uv_service() {
    create_uv_user
       echo "*** INFO: setup backend service"
       mkdir -p /etc/unifiedviews
       cp ${INSTALLDIR}/config-files/unifiedviews-backend.service /etc/init.d/unifiedviews-backend
       cp ${INSTALLDIR}/config-files/unifiedviews.conf /etc/unifiedviews/unifiedviews.conf
       mkdir -p /usr/local/unifiedviews/bin
       cp ${INSTALLDIR}/downloads/backend-service/usr/sbin/run_unifiedviews_backend /usr/local/unifiedviews/bin
       ln -s /usr/local/unifiedviews/bin/run_unifiedviews_backend /usr/sbin/run_unifiedviews_backend
      
       mkdir -p /var/log/unifiedviews/backend/
       chown ${UV_USER}:${UV_USER} /var/log/unifiedviews/backend/
       chmod +x /usr/sbin/run_unifiedviews_backend
       chmod +x /etc/init.d/unifiedviews-backend
       chmod +x ${INSTALLDIR}/downloads/backend-service/control/postinst
       ${INSTALLDIR}/downloads/backend-service/control/postinst configure
       chkconfig unifiedviews-backend on
       
	cp ${INSTALLDIR}/config-files/tomcat.service /etc/init.d/tomcat7
	cp ${INSTALLDIR}/config-files/tomcat-setenv.sh /usr/local/tomcat7/bin/setenv.sh
	echo >> /etc/default/tomcat7
	sed -i "s/^TOMCAT7_USER.*/TOMCAT7_USER=${UV_USER}/" /etc/default/tomcat7
	sed -i "s/^TOMCAT7_GROUP.*/TOMCAT7_GROUP=${UV_USER}/" /etc/default/tomcat7
	chown -Rf ${UV_USER}.${UV_USER} /usr/local/tomcat7/  
	chmod +x /etc/init.d/tomcat7
	chkconfig --add tomcat7
	chkconfig tomcat7 on
}

###############################################################################

build_uv_parts() {
    build_uv_core
    build_uv_plugins
}

replace_docker_uv() {
    cp  ${INSTALLDIR}/downloads/packages/backend*.jar ${DOCKERTARGETDIR}/uv-backend
    cp  ${INSTALLDIR}/downloads/packages/unifiedviews.war ${DOCKERTARGETDIR}/uv-frontend
    cp  ${INSTALLDIR}/downloads/packages/master.war ${DOCKERTARGETDIR}/uv-frontend
    cp  ${INSTALLDIR}/downloads/packages/plugins/* ${DOCKERTARGETDIR}/uv-add-dpus/dpus
    rm -rf ${DOCKERTARGETDIR}/uv-shared/lib/*
    cp  ${INSTALLDIR}/downloads/packages/lib/* ${DOCKERTARGETDIR}/uv-shared/lib
}

install_uv() {
    echo "**** INFO: install core part of system"
    if [ ! -d "/usr/local/unifiedviews/backend/working" ]
    then
	# Directories to dump everything
	mkdir -p /usr/local/unifiedviews
        mkdir -p /usr/local/unifiedviews/dpus
	mkdir -p /usr/local/unifiedviews/logs
	mkdir -p /usr/local/unifiedviews/dpu
	mkdir -p /usr/local/unifiedviews/config
	mkdir -p /usr/local/unifiedviews/lib
	mkdir -p /usr/local/unifiedviews/backend/working
	chmod -R g+rwX /usr/local/unifiedviews/backend/working
	
	create_dbtables
	
	pushd /usr/local/unifiedviews
         touch logs/frontend.log logs/frontend_err.log
	 if [ ! -d "${INSTALLDIR}/downloads/packages"  ] ; then
	     cp -r ${INSTALLDIR}/downloads/docker-unified-views/packages .
	     cp -r ${INSTALLDIR}/downloads/docker-unified-views/packages/lib /usr/local/unifiedviews
	 else
	     cp -r ${INSTALLDIR}/downloads/packages .
	     cp -r ${INSTALLDIR}/downloads/packages/lib /usr/local/unifiedviews
	 fi
	 if [ "$MUSERNAME" != "" ] ; then
	    cp ${INSTALLDIR}/config-files/email-config.properties ${INSTALLDIR}/config-files/email-config.properties.new
	    sed -i "s/%MAIL_PASSWORD%/$MPASSWORD/g" ${INSTALLDIR}/config-files/email-config.properties.new
	    sed -i "s/%MAIL_SERVER%/$MSERVER/g" ${INSTALLDIR}/config-files/email-config.properties.new
	 else
	     cp ${INSTALLDIR}/config-files/email-config.properties.default ${INSTALLDIR}/config-files/email-config.properties.new
	 fi
	 cat ${INSTALLDIR}/config-files/backend-config.${DB_CONNECTION}.properties ${INSTALLDIR}/config-files/email-config.properties.new > config/backend-config.properties
	 cat ${INSTALLDIR}/config-files/frontend-config.${DB_CONNECTION}.properties ${INSTALLDIR}/config-files/email-config.properties.new > config/frontend-config.properties
	 rm -f ${INSTALLDIR}/config-files/email-config.properties.new
	 cp ${INSTALLDIR}/config-files/startup.sh .
	 cp ${INSTALLDIR}/config-files/add-dpu.sh .
	 cp ${INSTALLDIR}/config-files/env-to-java-properties-file.sh .
         create_uv_service;
	 echo "Starting Service"
	 chmod +x /usr/local/unifiedviews/startup.sh
	 # Start everything and loadup the DPU's
	 ACCOUNT=${UV_USER} /usr/local/unifiedviews/startup.sh
        popd
    else
	echo "UV - Already setup"
    fi

    cp ${INSTALLDIR}/downloads/packages/*.war /usr/local/tomcat7/webapps
    chown -R ${UV_USER}:${UV_USER} /usr/local/tomcat7/webapps
}

##############################################################################
# Configure Postfix to be able to send an email message from the CMD Line.
#
install_mail() {
    if ! hash mail 2>/dev/null; then
	yum -y install mailutils sendmail postfix ca-certificates
    fi
}

config_mail() {
    if grep -Fq "smtp.gmail.com" /etc/postfix/main.cf
    then
	echo "PostFix already configured - do nothing"
    else
	echo "relayhost = [smtp.gmail.com]:587
smtp_sasl_auth_enable = yes
smtp_sasl_password_maps = hash:/etc/postfix/sasl_passwd
smtp_sasl_security_options = noanonymous
smtp_tls_CAfile = /etc/postfix/cacert.pem
smtp_use_tls = yes" >> /etc/postfix/main.cf
	echo "[smtp.gmail.com]:587    ${MUSERNAME}:${MPASSWORD}" > /etc/postfix/sasl_passwd
	chmod 400 /etc/postfix/sasl_passwd
	postmap /etc/postfix/sasl_passwd
	cat /etc/ssl/certs/ca*.pem | sudo tee -a /etc/postfix/cacert.pem
	/etc/init.d/postfix reload
	echo "Test mail from postfix" | mail -s "Test Postfix" ${MUSERNAME}
    fi
}

##############################################################################
# This will copy all the required files to a build directory which should then
# be sufficient to install the system on the remote system.

save_built() {
    mkdir -p ${INSTALLDIR}/${BUILDDIR}
    mkdir -p ${INSTALLDIR}/${BUILDDIR}/downloads
    mkdir -p ${INSTALLDIR}/${BUILDDIR}/downloads/backend-service
    pushd ${INSTALLDIR}/${BUILDDIR}
     cp -r ${INSTALLDIR}/downloads .
     rm -rf downloads/repository
     cp -r ${INSTALLDIR}/${BUILDDIR}/downloads/Core*/debian/unifiedviews-backend/src/deb/* ${INSTALLDIR}/${BUILDDIR}/downloads/backend-service
     rm -rf downloads/Core*
     rm -rf downloads/UV*
     rm -rf downloads/docker-*
     cp ${INSTALLDIR}/install_uv_components.sh .
     cp ${INSTALLDIR}/build*.sh .
     cp ${INSTALLDIR}/*.org .
     cp ${INSTALLDIR}/*.html .          
     cp -r ${INSTALLDIR}/config-files .
    popd
    # TAR it up for copying to remote system
    pushd ${INSTALLDIR}
     tar cvf uv_build.tar ${BUILDDIR}
     gzip -9 uv_build.tar
    popd
}

prepare_codi() {
    if [ "$CODI" = "yes" ] 
    then
	mkdir -p /u01/app/tomcat7
        ln -s /u01/app/tomcat7 /usr/local/tomcat7
	mkdir -p /u01/app/unifiedviews
	ln -s /u01/app/unifiedviews /usr/local/unifiedviews 
    fi
}

##############################################################################
# start installation of the build environment
##############################################################################

install_default;            # Main component installs
install_webbrowser;
install_editor;

if [ "$DOWNLOAD_ALLOWED" = "yes" ]
then
    # Normally, this list should contain all the downloads necessary when
    # installing off-line. However, there are some problems when it comes
    # to compiling with Maven (which will also download what is required).
    #
    # The assumption is that the base box has been created, which is very
    # close to the target machine (everything can then be moved to the
    # remote machine when needed for building).
    #

    download_tomcat7;
    download_maven3;
    download_uv;


    if [ ! -f "${INSTALLDIR}/downloads/ojdbc7.jar" -a ${ORACLESUPPORT} = "yes" ]
    then
       echo "ERROR: ojdbc7.jar must be downloaded from Oracle website and installed in downloads"
       exit -1;
    fi
    if [ ${MSSQLSUPPORT} = "yes" -a "${DB_CONNECTION}" = "mssql" -a ! -f "downloads/sqljdbc_6.0.6629.101_enu.tar.gz" ]
    then
       echo "Error: sqljdbc_6.0.6629.101_enu.tar.gz must be downloaded from microsoft website"
       exit -1;
    fi

fi



install_java
install_maven3
#install_tomcat7
install_local_virtuoso_jars

if [ "${DB_CONNECTION}" = "mysql" ] ; then
    install_mysql
fi

# MVN/JAVA/etc. should be accessible from this point on.
source ~/.bashrc



# last checks that some commands are installed

check_installed mvn
check_installed java
check_installed git


case "$STAGE" in
   DOWNLOAD_BUILD) 
	# Install some jdbc drivers
	   if [ ${ORACLESUPPORT} = "yes" ] ; then
		    install_oracle_jar
	   fi
	   if [ ${MSSQLSUPPORT} = "yes" ] ; then
		install_mssqlserver_jar
	   fi

	# Start to build, configure, etc. unifiedviews
	build_uv_parts

	;;
    SAVE_BUILT)
	replace_docker_uv;
	;;
    INSTALL_BUILT)
	replace_docker_uv;
	prepare_codi;
	copy_uv_plugins;
	install_uv;
	if [ "${CODI}" = "no" ]
	then
	    # Redirect homepages
	    mv /usr/share/doc/HTML/index.html /usr/share/doc/HTML/homepage_orig.html
	    cp /vagrant/homepage_uv.html /usr/share/doc/HTML/index.html
	fi
	# It should all be installed, so final checks
	check_uv_installation;
	;;
    *) echo "*** ERROR: Options are DOWNLOAD_BUILD|SAVE_BUILT|INSTALL_BUILT"
       exit -1
esac

###############################################################################
exit 0;
###############################################################################
