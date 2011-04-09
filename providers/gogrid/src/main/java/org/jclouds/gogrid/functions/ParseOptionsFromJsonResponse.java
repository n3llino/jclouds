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
package org.jclouds.gogrid.functions;

import java.util.SortedSet;

import javax.inject.Inject;

import org.jclouds.gogrid.domain.Option;
import org.jclouds.http.HttpResponse;
import org.jclouds.http.functions.ParseJson;

import com.google.common.base.Function;
import com.google.inject.Singleton;

/**
 * Parses the list of generic options.
 * 
 * GoGrid uses options as containers for id/name/descrOptiontion objects.
 * 
 * @author Oleksiy Yarmula
 */
@Singleton
public class ParseOptionsFromJsonResponse implements
      Function<HttpResponse, SortedSet<Option>> {

   private final ParseJson<GenericResponseContainer<Option>> json;

   @Inject
   ParseOptionsFromJsonResponse(ParseJson<GenericResponseContainer<Option>> json) {
      this.json = json;
   }

   @Override
   public SortedSet<Option> apply(HttpResponse arg0) {
      return json.apply(arg0).getList();
   }

}
