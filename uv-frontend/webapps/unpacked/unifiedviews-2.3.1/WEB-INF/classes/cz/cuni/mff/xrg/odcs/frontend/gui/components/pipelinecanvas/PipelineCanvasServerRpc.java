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

import com.vaadin.shared.communication.ServerRpc;

/**
 * Interface for calling RPC on server from client = JS part of component.
 * 
 * @author Bogo
 */
public interface PipelineCanvasServerRpc extends ServerRpc {

    /**
     * Occurs when new edge is created on graph canvas.
     * 
     * @param dpuFrom
     *            Id of start node.
     * @param dpuTo
     *            Id of end node.
     */
    public void onConnectionAdded(int dpuFrom, int dpuTo);

    /**
     * Occurs when edge is removed from graph canvas.
     * 
     * @param connectionId
     *            Id of removed edge.
     */
    public void onConnectionRemoved(int connectionId);

    /**
     * Occurs when detail of given DPUInstance is requested.
     * 
     * @param dpuId
     *            Id of dpu which detail is requested.
     */
    public void onDetailRequested(int dpuId);

    /**
     * Occurs when DPUInstance is removed from graph canvas.
     * 
     * @param dpuId
     *            Id of removed node.
     */
    public void onDpuRemoved(int dpuId);

    /**
     * Occurs when node on graph canvas is moved.
     * 
     * @param dpuId
     *            Id of moved node.
     * @param newX
     *            New X coordinate of node.
     * @param newY
     *            New Y coordinate of node.
     * @param autoAction
     *            If the action was initiated internally.
     */
    public void onDpuMoved(int dpuId, int newX, int newY, boolean autoAction);

    /**
     * Occurs on logging a message from graph canvas.
     * 
     * @param message
     *            Log message from JS part of the component.
     */
    public void onLogMessage(String message);

    /**
     * Occurs when debug up to given DPURecord is requested.
     * 
     * @param dpuId
     *            Id of node, where debug should end.
     */
    public void onDebugRequested(int dpuId);

    /**
     * Occurs when edit dialog for DataUnit name is requested.
     * 
     * @param edgeId
     *            Id of edge, which DataUnit name should be edited.
     */
    public void onDataUnitNameEditRequested(int edgeId);

    /**
     * Occurs when operation which should be available to undo happens.
     */
    public void onStoreHistory();

    /**
     * Occures when copy of given DPURecord is requested.
     * 
     * @param dpuId
     *            Id of node, which should be copied.
     * @param x
     * @param y
     */
    public void onDpuCopyRequested(int dpuId, int x, int y);

    /**
     * Occures when multiple DPU are selected/no longer selected.
     * 
     * @param selected
     */
    public void onMultipleDPUsSelected(boolean selected);
}
