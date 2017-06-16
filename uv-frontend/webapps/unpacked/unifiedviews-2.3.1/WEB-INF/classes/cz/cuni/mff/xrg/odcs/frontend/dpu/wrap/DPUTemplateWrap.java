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
import cz.cuni.mff.xrg.odcs.commons.app.dpu.DPUTemplateRecord;
import cz.cuni.mff.xrg.odcs.commons.app.user.User;

/**
 * Wrap {@link DPUTemplateRecord} to made work with configuration and
 * configuration dialog easier.
 *
 * @author Petyr
 */
public class DPUTemplateWrap extends DPURecordWrap {

    /**
     * Wrapped DPUTemplateRecord.
     */
    private final DPUTemplateRecord dpuTemplate;

    /**
     * Create wrap for DPUTemplateRecord.
     *
     * @param dpuTemplate
     */
    public DPUTemplateWrap(DPUTemplateRecord dpuTemplate, Locale locale, AppConfig appConfig, User user) {
        super(dpuTemplate, true, locale, appConfig, user);
        this.dpuTemplate = dpuTemplate;
    }

    /**
     * Get DPU template record.
     *
     * @return DPU template record
     */
    public DPUTemplateRecord getDPUTemplateRecord() {
        return dpuTemplate;
    }

}
