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
package cz.cuni.mff.xrg.odcs.frontend.doa.container.db;

import cz.cuni.mff.xrg.odcs.commons.app.dao.DataAccessRead;
import cz.cuni.mff.xrg.odcs.commons.app.dao.DataObject;
import cz.cuni.mff.xrg.odcs.commons.app.dao.db.DbQuery;
import cz.cuni.mff.xrg.odcs.commons.app.dao.db.DbQueryBuilder;
import cz.cuni.mff.xrg.odcs.commons.app.dao.db.DbQueryCount;
import cz.cuni.mff.xrg.odcs.frontend.doa.container.ClassAccessor;
import cz.cuni.mff.xrg.odcs.frontend.doa.container.InMemorySource;

/**
 * In memory source for container
 * 
 * @author Petyr
 * @param <T>
 */
public class DbInMemorySource<T extends DataObject>
        extends InMemorySource<T, DbQueryBuilder<T>, DbQuery<T>, DbQueryCount<T>> {

    /**
     * Constructor.
     * 
     * @param classAccessor
     *            Class accessor to use.
     */
    public DbInMemorySource(ClassAccessor<T> classAccessor) {
        super(classAccessor);
    }

    /**
     * Constructor.
     * 
     * @param classAccessor
     *            Class of data.
     * @param source
     *            Source of data.
     */
    public DbInMemorySource(ClassAccessor<T> classAccessor,
            DataAccessRead<T, DbQueryBuilder<T>, DbQuery<T>, DbQueryCount<T>> source) {
        super(classAccessor, source);
    }

}
