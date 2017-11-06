package com.mossle.core.query;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyFilterUtils {
    protected PropertyFilterUtils() {
    }

    public static void buildConfigurations(
            Collection<PropertyFilter> propertyFilters, StringBuilder buff,
            List<Object> params,Map<String, String> propertyNameTablePrefixs) {
        buildConfigurations(propertyFilters, buff, params, true,propertyNameTablePrefixs);
    }

    public static void buildConfigurations(
            Collection<PropertyFilter> propertyFilters, StringBuilder buff,
            List<Object> params, boolean checkWhere,Map<String, String> propertyNameTablePrefixs) {
        for (PropertyFilter propertyFilter : propertyFilters) {
            buildConfiguration(propertyFilter, buff, params, checkWhere,propertyNameTablePrefixs);
        }
    }

    public static void buildConfiguration(PropertyFilter propertyFilter,
            StringBuilder buff, List<Object> params,Map<String, String> propertyNameTablePrefixs) {
        buildConfiguration(propertyFilter, buff, params, true,propertyNameTablePrefixs);
    }

    public static void buildConfiguration(PropertyFilter propertyFilter,
            StringBuilder buff, List<Object> params, boolean checkWhere,Map<String, String> propertyNameTablePrefixs) {
    	
        if (checkWhere
                && (buff.toString().toLowerCase().indexOf("where") == -1)) {
            buff.append(" where ");
        } else {
            buff.append(" and ");
        }

        String propertyName = propertyFilter.getPropertyName();
        Object propertyValue = propertyFilter.getMatchValue();
        MatchType matchType = propertyFilter.getMatchType();

        switch (matchType) {
        case EQ:
        	if (propertyNameTablePrefixs.containsKey(propertyName)) {
        		buff.append(propertyNameTablePrefixs.get(propertyName)).append(".").append(propertyName).append("=?");
			}else{
				buff.append(propertyName).append("=?");
			}
            params.add(propertyValue);

            break;

        case NOT:
        	if (propertyNameTablePrefixs.containsKey(propertyName)) {
        		buff.append(propertyNameTablePrefixs.get(propertyName)).append(".").append(propertyName).append("=?");
			}else{
				buff.append(propertyName).append("<>?");
			}
            params.add(propertyValue);

            break;

        case LIKE:
        	if (propertyNameTablePrefixs.containsKey(propertyName)) {
        		buff.append(propertyNameTablePrefixs.get(propertyName)).append(".").append(propertyName).append(" like ?");
			}else{
				buff.append(propertyName).append(" like ?");
			}
            params.add("%" + propertyValue + "%");

            break;

        case LE:
        	if (propertyNameTablePrefixs.containsKey(propertyName)) {
        		buff.append(propertyNameTablePrefixs.get(propertyName)).append(".").append(propertyName).append("<=?");
			}else{
				buff.append(propertyName).append("<=?");
			}
            params.add(propertyValue);

            break;

        case LT:
        	if (propertyNameTablePrefixs.containsKey(propertyName)) {
        		buff.append(propertyNameTablePrefixs.get(propertyName)).append(".").append(propertyName).append("<?");
			}else{
				buff.append(propertyName).append("<?");
			}
            params.add(propertyValue);

            break;

        case GE:
        	if (propertyNameTablePrefixs.containsKey(propertyName)) {
        		buff.append(propertyNameTablePrefixs.get(propertyName)).append(".").append(propertyName).append(">=?");
			}else{
				buff.append(propertyName).append(">=?");
			}
            params.add(propertyValue);

            break;

        case GT:
        	if (propertyNameTablePrefixs.containsKey(propertyName)) {
        		buff.append(propertyNameTablePrefixs.get(propertyName)).append(".").append(propertyName).append(">?");
			}else{
				buff.append(propertyName).append(">?");
			}
            params.add(propertyValue);

            break;

        case IN:
        	if (propertyNameTablePrefixs.containsKey(propertyName)) {
        		buff.append(propertyNameTablePrefixs.get(propertyName)).append(".").append(propertyName).append(" in (?)");
			}else{
				buff.append(propertyName).append("in (?)");
			}
            params.add(propertyValue);

            break;

        case INL:
        	if (propertyNameTablePrefixs.containsKey(propertyName)) {
        		buff.append(propertyNameTablePrefixs.get(propertyName)).append(".").append(propertyName).append(" is null");
			}else{
				buff.append(propertyName).append(" is null");
			}
            break;

        case NNL:
        	if (propertyNameTablePrefixs.containsKey(propertyName)) {
        		buff.append(propertyNameTablePrefixs.get(propertyName)).append(".").append(propertyName).append(" is not null");
			}else{
				buff.append(propertyName).append(" is not null");
			}

            break;

        default:
        	if (propertyNameTablePrefixs.containsKey(propertyName)) {
        		buff.append(propertyNameTablePrefixs.get(propertyName)).append(".").append(propertyName).append("=?");
			}else{
				buff.append(propertyName).append("=?");
			}
            params.add(propertyValue);

            break;
        }
    }
}
