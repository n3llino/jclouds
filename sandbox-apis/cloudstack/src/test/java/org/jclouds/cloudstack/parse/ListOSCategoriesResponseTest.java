/**
 *
 * Copyright (C) 2011 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */
package org.jclouds.cloudstack.parse;

import java.util.Map;

import org.jclouds.cloudstack.functions.ParseIdToNameFromHttpResponse;
import org.jclouds.http.HttpResponse;
import org.testng.annotations.Test;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;

/**
 * 
 * @author Adrian Cole
 */
@Test(groups = "unit")
public class ListOSCategoriesResponseTest extends BaseItemParserTest<Map<Long, String>> {

   @Override
   public String resource() {
      return "/listoscategoriesresponse.json";
   }

   @Override
   public Map<Long, String> expected() {
      return ImmutableMap.<Long, String> builder().put(1l, "CentOS").put(2l, "Debian").put(3l, "Oracle")
            .put(4l, "RedHat").put(5l, "SUSE").put(6l, "Windows").put(7l, "Other").put(8l, "Novel").put(9l, "Unix")
            .put(10l, "Ubuntu").build();
   }

   @Override
   protected Function<HttpResponse, Map<Long, String>> getParser() {
      return i.getInstance(ParseIdToNameFromHttpResponse.class);
   }

   @SuppressWarnings({ "unchecked", "rawtypes" })
   @Override
   public Class<Map<Long, String>> type() {
      return (Class) Map.class;
   }
}
