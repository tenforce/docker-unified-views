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
package cz.cuni.mff.xrg.odcs.frontend.auxiliaries;

import com.vaadin.data.Validator;

import cz.cuni.mff.xrg.odcs.frontend.i18n.Messages;

/**
 * Validator for checking maximum length of input. Maximum length can be set in
 * constructor.
 * 
 * @author Bogo
 */
public final class MaxLengthValidator implements Validator {

    /**
     * Max length of description.
     */
    public static int DESCRIPTION_LENGTH = 512;

    private int maxLength = 1000;

    /**
     * Constructor.
     * 
     * @param maxLength
     *            Maximum length of input.
     */
    public MaxLengthValidator(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * Validates value for maximum length.
     * 
     * @param value
     *            value to validate
     * @throws com.vaadin.data.Validator.InvalidValueException
     *             If maximum length
     *             is exceeded.
     */
    @Override
    public void validate(Object value) throws InvalidValueException {
        if (value == null) {
            return;
        }
        if (value.getClass() == String.class) {
            String stringValue = (String) value;
            if (stringValue.length() > maxLength) {
                throw new Validator.InvalidValueException(Messages.getString("MaxLengthValidator.exception", maxLength, stringValue.length()));
            }
        }
    }
}
