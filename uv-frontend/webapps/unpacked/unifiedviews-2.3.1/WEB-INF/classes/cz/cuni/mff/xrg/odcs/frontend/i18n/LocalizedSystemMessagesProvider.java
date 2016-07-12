/**
 * This file is part of UnifiedViews.
 *
 * UnifiedViews is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * UnifiedViews is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with UnifiedViews.  If not, see <http://www.gnu.org/licenses/>.
 */
package cz.cuni.mff.xrg.odcs.frontend.i18n;

import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.SystemMessages;
import com.vaadin.server.SystemMessagesInfo;
import com.vaadin.server.SystemMessagesProvider;

public class LocalizedSystemMessagesProvider implements SystemMessagesProvider {

    private static final long serialVersionUID = -4891268300002329674L;

    private CustomizedSystemMessages systemMessages;

    public LocalizedSystemMessagesProvider() {
        this.systemMessages = new CustomizedSystemMessages();
        initLocalizedSystemMessages(this.systemMessages);
    }

    @Override
    public SystemMessages getSystemMessages(SystemMessagesInfo systemMessagesInfo) {
        return this.systemMessages;
    }

    private static void initLocalizedSystemMessages(CustomizedSystemMessages systemMessages) {
        systemMessages.setAuthenticationErrorCaption(Messages.getString("SystemMessages.authenticationErrorCaption"));
        systemMessages.setAuthenticationErrorMessage(Messages.getString("SystemMessages.authenticationErrorMessage"));

        systemMessages.setCommunicationErrorCaption(Messages.getString("SystemMessages.communicationErrorCaption"));
        systemMessages.setCommunicationErrorMessage(Messages.getString("SystemMessages.communicationErrorMessage"));

        systemMessages.setCookiesDisabledCaption(Messages.getString("SystemMessages.cookiesDisabledCaption"));
        systemMessages.setCookiesDisabledMessage(Messages.getString("SystemMessages.cookiesDisabledMessage"));

        systemMessages.setSessionExpiredCaption(Messages.getString("SystemMessages.sessionExpiredCaption"));
        systemMessages.setSessionExpiredMessage(Messages.getString("SystemMessages.sessionExpiredMessage"));

        systemMessages.setInternalErrorCaption(Messages.getString("SystemMessages.internalErrorCaption"));
        systemMessages.setInternalErrorMessage(Messages.getString("SystemMessages.internalErrorMessage"));

        systemMessages.setOutOfSyncCaption(Messages.getString("SystemMessages.outOfSyncCaption"));
        systemMessages.setOutOfSyncMessage(Messages.getString("SystemMessages.outOfSyncMessage"));
    }

}
