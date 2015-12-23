package org.cyberpredators.nanites.parser.test;

/*
 * YamlAdapterTest.java
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

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cyberpredators.nanites.parser.ModFactoryException;
import org.cyberpredators.nanites.parser.YamlAdapter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.esotericsoftware.yamlbeans.YamlException;

public class YamlAdapterTest {

	private YamlAdapter sut;

	@Before
	public void setUp() throws YamlException {
		Map<String, Object> bareYaml = new HashMap<String, Object>();
		bareYaml.put("stringKey", "stringValue");
		bareYaml.put("intKey", "1984");
		Map<String, Object> nestedBareYaml = new HashMap<String, Object>();
		nestedBareYaml.put("nestedStringKey", "nestedStringValue");
		bareYaml.put("nestedKey", nestedBareYaml);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 3; i++) {
			Map<String, Object> value = new HashMap<String, Object>();
			value.put("int", Integer.toString(i));
			list.add(value);
		}
		bareYaml.put("listKey", list);
		sut = new YamlAdapter(bareYaml);
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testInit() {
		assertThat(sut, notNullValue());
	}

	@Test
	public void testGetString() throws ModFactoryException {
		String stringValue = sut.getStringOrThrow("stringKey", "Key does not exist");
		assertThat(stringValue, is("stringValue"));
	}

	@Test
	public void testGetInt() throws ModFactoryException {
		int intValue = sut.getIntOrThrow("intKey", "Key does not exist");
		assertThat(intValue, is(1984));
	}

	@Test
	public void testGetYaml() throws ModFactoryException {
		YamlAdapter nestedYaml = sut.getYamlOrThrow("nestedKey", "Key does not exist");
		assertThat(nestedYaml.getStringOrThrow("nestedStringKey", "Key does not exist"), is("nestedStringValue"));
	}

	@Test
	public void testGetListYaml() throws ModFactoryException {
		List<YamlAdapter> list = sut.getListYamlOrThrow("listKey", "Key does not exist");
		assertThat(list.size(), is(3));
		assertThat(list.get(2).getIntOrThrow("int", "Key does not exist"), is (2));
	}

	@Test
	public void testMissingStringKey() throws ModFactoryException {
		exception.expect(ModFactoryException.class);
		exception.expectMessage("Key does not exist");
		sut.getStringOrThrow("missingKey", "Key does not exist");
	}

	@Test
	public void testMissingIntegerKey() throws ModFactoryException {
		exception.expect(ModFactoryException.class);
		exception.expectMessage("Key does not exist");
		sut.getIntOrThrow("missingKey", "Key does not exist");
	}

	@Test
	public void testMissingYamlKey() throws ModFactoryException {
		exception.expect(ModFactoryException.class);
		exception.expectMessage("Key does not exist");
		sut.getYamlOrThrow("missingKey", "Key does not exist");
	}

	@Test
	public void testContainsKey() {
		assertThat(sut.containsKey("stringKey"), is(true));
		assertThat(sut.containsKey("missingKey"), is(false));
	}
}
