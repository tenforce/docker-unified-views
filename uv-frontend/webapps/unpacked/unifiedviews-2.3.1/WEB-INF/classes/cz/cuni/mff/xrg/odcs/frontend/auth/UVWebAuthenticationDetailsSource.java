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
package cz.cuni.mff.xrg.odcs.frontend.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class UVWebAuthenticationDetailsSource implements
        AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

    private boolean behindProxy = false;

    /**
     * @param context
     *            the {@code HttpServletRequest} object.
     * @return the {@code WebAuthenticationDetails} containing information about the
     *         current request
     */
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        if (behindProxy)
            return new UVAuthenticationDetails(context);
        else
            return new WebAuthenticationDetails(context);
    }

    public void setBehindProxy(boolean behindProxy) {
        this.behindProxy = behindProxy;
    }

}
