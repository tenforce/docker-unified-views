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

import cz.cuni.mff.xrg.odcs.commons.app.pipeline.Pipeline;
import cz.cuni.mff.xrg.odcs.frontend.doa.container.ClassAccessorBase;
import cz.cuni.mff.xrg.odcs.frontend.gui.views.Utils;

/**
 * Accessor used for containers that just list the pipelines.
 * 
 * @author Petyr
 */
public class PipelineNameAccessor extends ClassAccessorBase<Pipeline> {

    /**
     * Constructor.
     */
    public PipelineNameAccessor() {
        super(Pipeline.class);

        addNon(Long.class, "id", new ColumnGetter<Long>() {
            @Override
            public Long get(Pipeline object) {
                return object.getId();
            }
        });

        addNon(String.class, "name", new ColumnGetter<String>() {
            @Override
            public String get(Pipeline object) {
                String name = object.getName();
                return name.length() > Utils.getColumnMaxLenght() ? name.substring(0, Utils.getColumnMaxLenght() - 3) + "..." : name;
            }
        });

        addNon(String.class, "description", new ColumnGetter<String>() {
            @Override
            public String get(Pipeline object) {
                String description = object.getDescription();
                if (description == null) {
                    return null;
                }
                return description.length() > Utils.getColumnMaxLenght() ? description.substring(0, Utils.getColumnMaxLenght() - 3) + "..." : description;
            }
        });

    }
}
