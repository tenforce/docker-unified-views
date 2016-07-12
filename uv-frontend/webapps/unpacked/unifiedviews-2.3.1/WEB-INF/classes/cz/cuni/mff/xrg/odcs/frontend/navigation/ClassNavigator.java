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
import cz.cuni.mff.xrg.odcs.frontend.gui.views.Presenter;

/**
 * Interface for application navigator.
 * 
 * @author Petyr
 */
public interface ClassNavigator {

    /**
     * Navigate to given view.
     * 
     * @param url
     */
    void navigateTo(String url);

    /**
     * Navigate to given target ie. {@link Presenter} or {@link View}. The
     * object we are navigating to must have the @link Address} annotation.
     * 
     * @param target
     */
    void navigateTo(Class<?> target);

    /**
     * Navigate to given target ie. {@link Presenter} or {@link View}. The
     * object we are navigating to must have the @link Address} annotation.
     * 
     * @param target
     * @param parameters
     */
    void navigateTo(Class<?> target, String parameters);

}
