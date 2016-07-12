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
package cz.cuni.mff.xrg.odcs.frontend.dpu.validator;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.cuni.mff.xrg.odcs.commons.app.data.DataUnitDescription;
import cz.cuni.mff.xrg.odcs.commons.app.dpu.DPUExplorer;
import cz.cuni.mff.xrg.odcs.commons.app.dpu.DPUTemplateRecord;
import cz.cuni.mff.xrg.odcs.commons.app.module.DPUValidator;
import cz.cuni.mff.xrg.odcs.commons.app.module.DPUValidatorException;
import cz.cuni.mff.xrg.odcs.frontend.i18n.Messages;

/**
 * Check for duplicity in names of input and output data units.
 * 
 * @author Škoda Petr
 */
@Component
class DataUnitNamesValidator implements DPUValidator {

    @Autowired
    private DPUExplorer explorer;

    @Override
    public void validate(DPUTemplateRecord dpu, Object dpuInstance) throws DPUValidatorException {
        check(explorer.getInputs(dpu));
        check(explorer.getOutputs(dpu));
    }

    /**
     * Check given list for duplicity names, if there are some then throws an
     * exception.
     * 
     * @param dataUnits
     * @throws DPUValidatorException
     */
    private void check(List<DataUnitDescription> dataUnits) throws DPUValidatorException {
        HashSet<String> names = new HashSet<>();
        for (DataUnitDescription desc : dataUnits) {
            if (names.contains(desc.getName())) {
                // name collision
                throw new DPUValidatorException(Messages.getString("DataUnitNamesValidator.same.dataunit", desc.getName()));
            } else {
                names.add(desc.getName());
            }
        }
    }

}
