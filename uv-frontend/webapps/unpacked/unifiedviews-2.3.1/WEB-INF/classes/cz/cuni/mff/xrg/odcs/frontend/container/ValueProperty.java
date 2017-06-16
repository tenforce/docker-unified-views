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

import com.vaadin.data.Property;
import cz.cuni.mff.xrg.odcs.commons.app.dao.DataObject;

/**
 * Holds property which is associated with single object.
 * 
 * @author Petyr
 * @param <T>
 */
class ValueProperty<V, T extends DataObject> implements Property<V> {

    private final GeneralProperty<V, T> generalProperty;

    private final T object;

    ValueProperty(GeneralProperty<V, T> generalProperty, Long id) {
        this.generalProperty = generalProperty;
        this.object = generalProperty.container.getObject(id);
        if (this.object == null) {

        }
    }

    @Override
    public V getValue() {
        return generalProperty.getValueForObject(object);
    }

    @Override
    public void setValue(V newValue)
            throws com.vaadin.data.Property.ReadOnlyException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<? extends V> getType() {
        return generalProperty.getType();
    }

    @Override
    public boolean isReadOnly() {
        return generalProperty.isReadOnly();
    }

    @Override
    public void setReadOnly(boolean newStatus) {
        throw new UnsupportedOperationException();
    }

}
