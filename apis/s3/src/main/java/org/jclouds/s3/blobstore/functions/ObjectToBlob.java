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
package org.jclouds.s3.blobstore.functions;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jclouds.s3.domain.S3Object;
import org.jclouds.blobstore.domain.Blob;
import org.jclouds.blobstore.domain.Blob.Factory;

import com.google.common.base.Function;

/**
 * @author Adrian Cole
 */
@Singleton
public class ObjectToBlob implements Function<S3Object, Blob> {
   private final Blob.Factory blobFactory;
   private final ObjectToBlobMetadata object2BlobMd;

   @Inject
   ObjectToBlob(Factory blobFactory, ObjectToBlobMetadata object2BlobMd) {
      this.blobFactory = blobFactory;
      this.object2BlobMd = object2BlobMd;
   }

   public Blob apply(S3Object from) {
      if (from == null)
         return null;
      Blob blob = blobFactory.create(object2BlobMd.apply(from.getMetadata()));
      blob.setPayload(checkNotNull(from.getPayload(), "payload: " + from));
      blob.setAllHeaders(from.getAllHeaders());
      return blob;
   }
}
