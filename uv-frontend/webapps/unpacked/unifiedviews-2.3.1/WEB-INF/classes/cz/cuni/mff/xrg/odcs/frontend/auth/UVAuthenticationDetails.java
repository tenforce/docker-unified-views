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

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class UVAuthenticationDetails extends WebAuthenticationDetails {

    private String forwardedHost;

    private String host;

    private String scheme;

    private static final String HTTP_HEADER_FORWARDED_HOST = "X-Forwarded-Host";

    private static final String HTTP_HEADER_HOST = "Host";

    private static final String HTTP_HEADER_SCHEME = "Scheme";

    public UVAuthenticationDetails(HttpServletRequest request) {
        super(request);

        this.forwardedHost = request.getHeader(HTTP_HEADER_FORWARDED_HOST);
        this.host = request.getHeader(HTTP_HEADER_HOST);
        this.scheme = request.getHeader(HTTP_HEADER_SCHEME);
    }

    public String getForwardedHost() {
        return forwardedHost;
    }

    public String getHost() {
        return host;
    }

    public String getScheme() {
        return scheme;
    }

}
