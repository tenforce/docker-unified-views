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
package cz.cuni.mff.xrg.odcs.frontend.auxiliaries.download;

import java.io.IOException;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;

/**
 * This specializes {@link FileDownloader} in a way, such that both the file
 * name and content can be determined on-demand, i.e. when the user has clicked
 * the component.
 */
public class OnDemandFileDownloader extends FileDownloader {

    private static final long serialVersionUID = 1L;

    private final OnDemandStreamResource onDemandStreamResource;

    /**
     * Constructor.
     * 
     * @param onDemandStreamResource
     *            Resource to use for download.
     */
    public OnDemandFileDownloader(
            OnDemandStreamResource onDemandStreamResource) {
        super(new StreamResource(onDemandStreamResource, ""));
        this.onDemandStreamResource = onDemandStreamResource;
    }

    @Override
    public boolean handleConnectorRequest(VaadinRequest request,
            VaadinResponse response, String path)
            throws IOException {
        getResource().setFilename(onDemandStreamResource.getFilename());
        return super.handleConnectorRequest(request, response, path);
    }

    private StreamResource getResource() {
        return (StreamResource) this.getResource("dl");
    }
}
