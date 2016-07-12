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
package cz.cuni.mff.xrg.odcs.frontend.dpu.dialog;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cz.cuni.mff.xrg.odcs.commons.app.conf.AppConfig;
import cz.cuni.mff.xrg.odcs.commons.app.user.User;
import eu.unifiedviews.dpu.config.vaadin.ConfigDialogContext;

/**
 * Implementation of {@link ConfigDialogContext}.
 *
 * @author Petyr
 */
public class ConfigDialogContextImpl implements ConfigDialogContext {

    /**
     * True in case that the dialog is used for template, false otherwise.
     */
    private final boolean isTemplate;

    private final Locale locale;

    protected AppConfig appConfig;

    private User loggedUser;

    /**
     * Constructor.
     */
    public ConfigDialogContextImpl(boolean isTemplate, Locale locale, AppConfig appConfig, User loggedUser) {
        this.isTemplate = isTemplate;
        this.locale = locale;
        this.appConfig = appConfig;
        this.loggedUser = loggedUser;
    }

    @Override
    public boolean isTemplate() {
        return this.isTemplate;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public Map<String, String> getEnvironment() {
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<Object, Object> entry : appConfig.getProperties().entrySet()) {
            result.put((String) entry.getKey(), (String) entry.getValue());
        }
        return result;
    }

    @Override
    public String getUserExternalId() {
        return this.loggedUser.getExternalIdentifier();
    }

    @Override
    public Long getUserId() {
        return this.loggedUser.getId();
    }

}
