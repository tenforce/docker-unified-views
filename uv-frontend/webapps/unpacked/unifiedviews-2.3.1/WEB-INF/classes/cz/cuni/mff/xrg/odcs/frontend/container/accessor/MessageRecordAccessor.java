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

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cz.cuni.mff.xrg.odcs.commons.app.execution.message.MessageRecord;
import cz.cuni.mff.xrg.odcs.commons.app.execution.message.MessageRecordType;
import cz.cuni.mff.xrg.odcs.frontend.doa.container.ClassAccessor;
import cz.cuni.mff.xrg.odcs.frontend.i18n.Messages;

/**
 * @author Bogo
 */
public class MessageRecordAccessor implements ClassAccessor<MessageRecord> {

    private final List<String> all = Arrays.asList("id", "type", "time", "dpu", "shortMessage", "dpuInstance.id");

    private final List<String> sortable = Arrays.asList("time", "type", "dpu");

    private final List<String> filterable = Arrays.asList("time", "type", "dpu", "shortMessage");

    private final List<String> visible = Arrays.asList("type", "time", "dpu", "shortMessage");

    private final List<String> toFetch = Arrays.asList("dpuInstance");

    @Override
    public List<String> all() {
        return all;
    }

    @Override
    public List<String> sortable() {
        return sortable;
    }

    @Override
    public List<String> filterable() {
        return filterable;
    }

    @Override
    public List<String> visible() {
        return visible;
    }

    @Override
    public List<String> toFetch() {
        return toFetch;
    }

    @Override
    public Class<MessageRecord> getEntityClass() {
        return MessageRecord.class;
    }

    @Override
    public String getColumnName(String id) {
        switch (id) {
            case "time":
                return Messages.getString("MessageRecordAccessor.timestamp");
            case "dpu":
                return Messages.getString("MessageRecordAccessor.dpu");
            case "shortMessage":
                return Messages.getString("MessageRecordAccessor.short");
            case "type":
                return Messages.getString("MessageRecordAccessor.type");
            default:
                return id;
        }
    }

    @Override
    public Object getValue(MessageRecord object, String id) {
        switch (id) {
            case "id":
                return object.getId();
            case "time":
                return object.getTime();
            case "type":
                return object.getType();
            case "dpu":
                if (object.getDpuInstance() != null) {
                    return object.getDpuInstance().getName();
                } else {
                    return "";
                }
            case "shortMessage":
                return object.getShortMessage();
            case "dpuInstance.id":
                if (object.getDpuInstance() != null) {
                    return object.getDpuInstance().getId();
                } else {
                    return null;
                }
            default:
                return null;
        }
    }

    @Override
    public Class<?> getType(String id) {
        switch (id) {
            case "id":
            case "dpuInstance.id":
                return Long.class;
            case "time":
                return Date.class;
            case "type":
                return MessageRecordType.class;
            case "dpu":
            case "shortMessage":
                return String.class;
            default:
                return null;
        }
    }
}
