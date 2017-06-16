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
package cz.cuni.mff.xrg.odcs.frontend.container.rdf;

import org.vaadin.addons.lazyquerycontainer.Query;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;
import org.vaadin.addons.lazyquerycontainer.QueryFactory;

import cz.cuni.mff.xrg.odcs.frontend.i18n.Messages;

/**
 * Simple {@link QueryFactory} for constructing RDF queries.
 * 
 * @author Bogo
 */
public class RDFQueryFactory implements QueryFactory {

    /**
     * Construct query from definition.
     * 
     * @param queryDefinition
     *            Query definition.
     * @return Query.
     */
    @Override
    public Query constructQuery(QueryDefinition queryDefinition) {
        if (queryDefinition.getClass() != RDFQueryDefinition.class) {
            throw new UnsupportedOperationException(Messages.getString("RDFQueryFactory.exception"));
        }
        return new RDFQuery((RDFQueryDefinition) queryDefinition);
    }

}
