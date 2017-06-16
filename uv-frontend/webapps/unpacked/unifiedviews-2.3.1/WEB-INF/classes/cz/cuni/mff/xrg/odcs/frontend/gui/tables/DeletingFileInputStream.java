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
package cz.cuni.mff.xrg.odcs.frontend.gui.tables;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * This code is from https://code.google.com/p/tableexport-for-vaadin/source/browse/src/com/vaadin/addon/tableexport/DeletingFileInputStream.java?name=vaadin7
 * 
 * This input stream deletes the given file when the InputStream is closed; intended to be used with
 * temporary files.
 * Code obtained from: http://vaadin.com/forum/-/message_boards/view_message/159583
 */
class DeletingFileInputStream extends FileInputStream {

    /** The file. */
    protected File file = null;

    /**
     * Instantiates a new deleting file input stream.
     * 
     * @param file
     *            the file
     * @throws FileNotFoundException
     *             the file not found exception
     */
    public DeletingFileInputStream(final File file) throws FileNotFoundException {
        super(file);
        this.file = file;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.FileInputStream#close()
     */
    @Override
    public void close() throws IOException {
        super.close();
        FileUtils.deleteQuietly(file);
    }
}
