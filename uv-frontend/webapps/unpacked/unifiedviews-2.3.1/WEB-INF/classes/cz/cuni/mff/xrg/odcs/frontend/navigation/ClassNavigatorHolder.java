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

/**
 * The navigator can not be created outside UI. For this reason we use this
 * object as a proxy. It will provide access to the navigator for the
 * application.
 * The navigator class must be set before first use, bet in the UI.init method.
 * 
 * @author Petyr
 */
public class ClassNavigatorHolder implements ClassNavigator {

    private ClassNavigator navigator;

    @Override
    public void navigateTo(String url) {
        navigator.navigateTo(url);
    }

    @Override
    public void navigateTo(Class<?> target) {
        navigator.navigateTo(target);
    }

    @Override
    public void navigateTo(Class<?> target, String parameters) {
        navigator.navigateTo(target, parameters);
    }

    /**
     * Sets navigator.
     * 
     * @param navigator
     *            Navigator to use.
     */
    public void setNavigator(ClassNavigator navigator) {
        this.navigator = navigator;
    }

    /**
     * Gets current navigator.
     * 
     * @return Navigator.
     */
    public ClassNavigator getNavigator() {
        return this.navigator;
    }

}
