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
 * This property represent a field and respective column in container.
 * 
 * @author Petyr
 * @param <T>
 */
class GeneralProperty<V, T extends DataObject> implements Property<V> {

    String fieldName;

    Class<V> type;

    ReadOnlyContainer<T> container;

    GeneralProperty(Class<V> type, String fieldName, ReadOnlyContainer<T> container) {
        // ...
        this.fieldName = fieldName;
        this.type = type;
        this.container = container;
    }

    /**
     * Create instance of this property binded for given object (id).
     * 
     * @param id
     * @return
     */
    ValueProperty<V, T> bind(Long id) {
        return new ValueProperty<>(this, id);
    }

    @SuppressWarnings("unchecked")
    V getValueForObject(T object) {
        return (V) container.getClassAccessor().getValue(object, fieldName);
    }

    @Override
    public V getValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValue(V newValue)
            throws com.vaadin.data.Property.ReadOnlyException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<? extends V> getType() {
        return type;
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }

    @Override
    public void setReadOnly(boolean newStatus) {
        throw new UnsupportedOperationException();
    }

}
