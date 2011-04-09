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
package org.jclouds.ibmdev.predicates;

import javax.annotation.Resource;
import javax.inject.Singleton;

import org.jclouds.ibmdev.IBMDeveloperCloudClient;
import org.jclouds.ibmdev.domain.Volume;
import org.jclouds.logging.Logger;

import com.google.common.base.Predicate;
import com.google.inject.Inject;

/**
 * 
 * @author Adrian Cole
 */
@Singleton
public class VolumeUnmounted implements Predicate<Volume> {

   private final IBMDeveloperCloudClient client;

   @Resource
   protected Logger logger = Logger.NULL;

   @Inject
   public VolumeUnmounted(IBMDeveloperCloudClient client) {
      this.client = client;
   }

   public boolean apply(Volume volume) {
      logger.trace("looking for state on volume %s", volume);
      volume = client.getVolume(volume.getId());
      if (volume == null)
         return false;
      logger.trace("%s: looking for volume state %s: currently: %s", volume.getId(),
               Volume.State.UNMOUNTED, volume.getState());
      return volume.getState() == Volume.State.UNMOUNTED;
   }

}
