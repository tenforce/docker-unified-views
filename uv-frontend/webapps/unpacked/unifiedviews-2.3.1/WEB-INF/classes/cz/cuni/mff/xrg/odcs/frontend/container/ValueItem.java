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

import java.util.Collection;

import com.vaadin.data.Item;
import com.vaadin.data.Property;

/**
 * {@link Item} implementation for use in {@link ReadOnlyContainer}.
 * 
 * @author petyr
 */
public class ValueItem implements Item {

    private final ReadOnlyContainer<?> container;

    private final Long objectId;

    /**
     * Constructor.
     * 
     * @param container
     *            Container with data.
     * @param objectId
     *            Id of item.
     */
    ValueItem(ReadOnlyContainer<?> container, Long objectId) {
        this.container = container;
        this.objectId = objectId;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Property getItemProperty(Object id) {
        return container.getProperties().get(id).bind(objectId);
    }

    @Override
    public Collection<?> getItemPropertyIds() {
        // every item has all values
        return container.getProperties().keySet();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean addItemProperty(Object id, Property property)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeItemProperty(Object id)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * Return embedded object id.
     * 
     * @return embedded object id
     */
    public Long getId() {
        return objectId;
    }

}
