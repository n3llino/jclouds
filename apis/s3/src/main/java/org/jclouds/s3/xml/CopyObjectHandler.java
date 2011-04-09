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
package org.jclouds.s3.xml;

import java.util.Date;

import javax.inject.Inject;

import org.jclouds.s3.domain.ObjectMetadata;
import org.jclouds.s3.domain.internal.CopyObjectResult;
import org.jclouds.date.DateService;
import org.jclouds.http.functions.ParseSax;

/**
 * Parses the response from Amazon S3 COPY Object command.
 * <p/>
 * CopyObjectResult is the document we expect to parse.
 * 
 * @see <a href= "http://docs.amazonwebservices.com/AmazonS3/2006-03-01/RESTObjectCOPY.html" />
 * @author Adrian Cole
 */
public class CopyObjectHandler extends ParseSax.HandlerWithResult<ObjectMetadata> {

   private CopyObjectResult metadata;
   private StringBuilder currentText = new StringBuilder();
   @Inject
   private DateService dateParser;
   private Date currentLastModified;
   private String currentETag;

   public ObjectMetadata getResult() {
      return metadata;
   }

   public void endElement(String uri, String name, String qName) {
      if (qName.equals("ETag")) {
         this.currentETag = currentText.toString().trim();
      } else if (qName.equals("LastModified")) {
         this.currentLastModified = dateParser.iso8601DateParse(currentText.toString().trim());
      } else if (qName.equals("CopyObjectResult")) {
         metadata = new CopyObjectResult(currentLastModified, currentETag);
      }
      currentText = new StringBuilder();
   }

   public void characters(char ch[], int start, int length) {
      currentText.append(ch, start, length);
   }
}
