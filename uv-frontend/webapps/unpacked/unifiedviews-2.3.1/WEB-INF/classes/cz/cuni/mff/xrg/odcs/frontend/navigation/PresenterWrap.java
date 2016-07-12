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
package cz.cuni.mff.xrg.odcs.frontend.navigation;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import cz.cuni.mff.xrg.odcs.frontend.gui.views.Presenter;

/**
 * Wrap presenter class so it can be used in navigator as a view. When
 * compare with {@link #equals(java.lang.Object)} with class use wrapped
 * presenter.
 * 
 * @author Petyr
 */
class PresenterWrap implements View {

    /**
     * Wrapped presenter.
     */
    private final Presenter presenter;

    public PresenterWrap(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        presenter.setParameters(ParametersHandler.getConfiguration(event.getParameters()));
    }

    public Presenter getPresenter() {
        return presenter;
    }

    /**
     * Enter presenter with last parameters from {@link #enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)} and return result of
     * {@link Presenter#enter(java.lang.Object)}.
     * 
     * @return
     */
    public Object enterPresenter() {
        return presenter.enter();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Class<?>) {
            return (Class<?>) obj == presenter.getClass();
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return presenter.hashCode();
    }

}