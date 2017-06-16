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
package cz.cuni.mff.xrg.odcs.frontend.container.accessor;

import java.util.Date;

import cz.cuni.mff.xrg.odcs.commons.app.execution.log.Log;
import cz.cuni.mff.xrg.odcs.frontend.doa.container.ClassAccessorBase;
import cz.cuni.mff.xrg.odcs.frontend.i18n.Messages;

/**
 * Class accessor for logs.
 * 
 * @author Petyr
 */
public class NewLogAccessor extends ClassAccessorBase<Log> {

    /**
     * Constructor.
     */
    public NewLogAccessor() {
        super(Log.class);
        addInvisible(Long.class, "id", new ColumnGetter<Long>() {
            @Override
            public Long get(Log object) {
                return object.getId();
            }
        });

        add(Integer.class, "logLevel", Messages.getString("NewLogAccessor.type"), new ColumnGetter<Integer>() {
            @Override
            public Integer get(Log object) {
                return object.getLogLevel();
            }
        });

        add(Date.class, "timestamp", Messages.getString("NewLogAccessor.timestamp"), new ColumnGetter<Date>() {
            @Override
            public Date get(Log object) {
                return new Date(object.getTimestamp());
            }
        });

        addInvisible(Long.class, "execution", new ColumnGetter<Long>() {
            @Override
            public Long get(Log object) {
                return object.getExecution();
            }
        });

        add(Long.class, "dpu", Messages.getString("NewLogAccessor.dpu"), false, true, new ColumnGetter<Long>() {
            @Override
            public Long get(Log object) {
                return object.getDpu();
            }
        });

        add(String.class, "message", Messages.getString("NewLogAccessor.message"), new ColumnGetter<String>() {
            @Override
            public String get(Log object) {
                return object.getMessage();
            }
        });

    }

}
