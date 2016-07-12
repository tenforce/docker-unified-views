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
package cz.cuni.mff.xrg.odcs.frontend.gui.views;

/**
 * Interface for presenter that can change the application data.
 * 
 * @author Petyr
 */
public interface SavablePresenter extends Presenter {

    /**
     * Return true if there are unsaved changes in presenter.
     * 
     * @return If is modified
     */
    boolean isModified();

    /**
     * Save data in presenter.
     * It is called when isModified returns true and user decides to save the changes
     */
    void save();

}
