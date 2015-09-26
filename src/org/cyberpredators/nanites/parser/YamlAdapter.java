package org.cyberpredators.nanites.parser;

/*
 * YamlAdapter.java
 * Copyright (C) Remi Even 2015
 * 
 * This file is part of CyberNanites2000.
 * 
 * CyberNanites2000 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * CyberNanites2000 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with CyberNanites2000. If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.Map;

public class YamlAdapter {

	private final Map<? extends String, ?> yaml;

	public YamlAdapter(Map<? extends String, ?> yaml) {
		this.yaml = yaml;
	}

	public String getStringOrThrow(String key, String exceptionMessage) throws ModFactoryException {
		if (!yaml.containsKey(key))
			throw new ModFactoryException(exceptionMessage);
		return (String) yaml.get(key);
	}

	public int getIntOrThrow(String key, String exceptionMessage) throws ModFactoryException {
		if (!yaml.containsKey(key))
			throw new ModFactoryException(exceptionMessage);
		return Integer.parseInt((String) yaml.get(key));
	}

	@SuppressWarnings("unchecked")
	public YamlAdapter getYamlOrThrow(String key, String exceptionMessage) throws ModFactoryException {
		if (!yaml.containsKey(key))
			throw new ModFactoryException(exceptionMessage);
		return new YamlAdapter((Map<? extends String, ?>) yaml.get(key));
	}
}
