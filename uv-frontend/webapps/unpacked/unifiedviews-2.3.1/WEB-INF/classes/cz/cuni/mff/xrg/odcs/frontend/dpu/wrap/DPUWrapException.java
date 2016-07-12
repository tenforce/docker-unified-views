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
package cz.cuni.mff.xrg.odcs.frontend.dpu.wrap;

/**
 * Exception used to wrap other exception that can occurs
 * during working with {@link DPURecordWrap} and it's descendants.
 * 
 * @author Petyr
 */
public class DPUWrapException extends Exception {

    /**
     * Constructor.
     * 
     * @param cause
     *            Cause of the exception.
     */
    public DPUWrapException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor.
     * 
     * @param message
     * @param cause
     */
    public DPUWrapException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     * 
     * @param message
     */
    public DPUWrapException(String message) {
        super(message);
    }

}
