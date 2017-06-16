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

import java.io.Serializable;
import java.util.Date;

/**
 * Token with a signed information about the user who authenticates with this
 * token.
 * TODO override writeObject and readObject to serialize effectively
 * 
 * @author Jan Vojt
 */
public class RememberMeToken implements Serializable {

    /**
     * Remember-me cookie separator.
     */
    public static final Character SEP = '#';

    private final String username;

    private final Date created;

    private final String hash;

    /**
     * Constructor.
     * 
     * @param username
     *            Username.
     * @param created
     *            Date of creation.
     * @param hash
     *            Password hash.
     */
    public RememberMeToken(String username, Date created, String hash) {
        this.username = username;
        this.created = created;
        this.hash = hash;
    }

    /**
     * Get username.
     * 
     * @return Username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get date created.
     * 
     * @return Date created.
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Get password hash.
     * 
     * @return Password hash.
     */
    public String getHash() {
        return hash;
    }

}
