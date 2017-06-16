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

import org.vaadin.addons.lazyquerycontainer.LazyQueryContainer;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;
import org.vaadin.addons.lazyquerycontainer.QueryFactory;
import org.vaadin.addons.lazyquerycontainer.QueryView;

/**
 * Container for lazy querying of RDF data. Works with SELECT or CONSTRUCT queries.
 * 
 * @author Bogo
 *         TODO (petyr) we should use DbAccess and ReadOnly container instead to unify the data access
 */
public class RDFLazyQueryContainer extends LazyQueryContainer {

    /**
     * Constructor.
     * 
     * @param queryView
     */
    public RDFLazyQueryContainer(QueryView queryView) {
        super(queryView);
    }

    /**
     * Constructor.
     * 
     * @param queryDefinition
     * @param queryFactory
     */
    public RDFLazyQueryContainer(QueryDefinition queryDefinition, QueryFactory queryFactory) {
        super(queryDefinition, queryFactory);
    }

    /**
     * Constructor.
     * 
     * @param queryFactory
     * @param idPropertyId
     * @param batchSize
     * @param compositeItems
     */
    public RDFLazyQueryContainer(QueryFactory queryFactory, Object idPropertyId, int batchSize, boolean compositeItems) {
        super(queryFactory, idPropertyId, batchSize, compositeItems);
    }

}
