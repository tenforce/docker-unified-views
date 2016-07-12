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
package cz.cuni.mff.xrg.odcs.frontend.container;

import java.util.List;

/**
 * Interface for container self description.
 * 
 * @author Petyr
 */
public interface ContainerDescription {

    /**
     * Return ids of columns that are filterable. If there are no filters
     * available then return empty List.
     * 
     * @return ids of columns that are filterable
     */
    public List<String> getFilterables();

    /**
     * Return name for column of given id.
     * 
     * @param id
     * @return name for column of given id
     */
    public String getColumnName(String id);

    /**
     * Return ids of column that are visible.
     * 
     * @return ids of column that are visible
     */
    public List<String> getVisibles();

}
