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

import java.util.Locale;

import cz.cuni.mff.xrg.odcs.commons.app.conf.AppConfig;
import cz.cuni.mff.xrg.odcs.commons.app.dpu.DPUInstanceRecord;
import cz.cuni.mff.xrg.odcs.commons.app.facade.DPUFacade;
import cz.cuni.mff.xrg.odcs.commons.app.user.User;

/**
 * Wrap {@link DPUInstanceRecord} to made work with configuration and
 * configuration dialog easier.
 *
 * @author Petyr
 */
public class DPUInstanceWrap extends DPURecordWrap {

    private final DPUFacade dpuFacade;

    /**
     * Wrapped DPUTemplateRecord.
     */
    private final DPUInstanceRecord dpuInstance;

    /**
     * Create wrap for DPUTemplateRecord.
     *
     * @param dpuTemplate
     * @param dpuFacade
     */
    public DPUInstanceWrap(DPUInstanceRecord dpuTemplate, DPUFacade dpuFacade, Locale locale, AppConfig appConfig, User loggedUser) {
        super(dpuTemplate, false, locale, appConfig, loggedUser);
        this.dpuFacade = dpuFacade;
        this.dpuInstance = dpuTemplate;
    }

    /**
     * Save wrapped DPUInstanceInto database. To save configuration from dialog
     * as well call {{@link #saveConfig()} first.
     */
    public void save() {
        dpuFacade.save(dpuInstance);
    }

    /**
     * Get DPUInstance record.
     *
     * @return DPUInstance record
     */
    public DPUInstanceRecord getDPUInstanceRecord() {
        return dpuInstance;
    }
}
