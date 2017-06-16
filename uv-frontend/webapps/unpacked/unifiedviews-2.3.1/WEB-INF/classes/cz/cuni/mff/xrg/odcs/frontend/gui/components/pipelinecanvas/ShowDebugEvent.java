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
package cz.cuni.mff.xrg.odcs.frontend.gui.components.pipelinecanvas;

import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Event;

import cz.cuni.mff.xrg.odcs.commons.app.pipeline.Pipeline;
import cz.cuni.mff.xrg.odcs.commons.app.pipeline.graph.Node;

/**
 * Event for debug request on {@link PipelineCanvas}.
 * 
 * @author Bogo
 */
public class ShowDebugEvent extends Event {
    private Node debugNode;

    /**
     * Default constructor. Inform, that debug was requested for current {@link Pipeline} and given {@link Node}.
     * 
     * @param source
     *            Source component.
     * @param debugNode
     *            {@link Node} where debug should end.
     */
    public ShowDebugEvent(Component source, Node debugNode) {
        super(source);
        this.debugNode = debugNode;
    }

    /**
     * Get debug node.
     * 
     * @return debug node
     */
    public Node getDebugNode() {
        return debugNode;
    }
}
