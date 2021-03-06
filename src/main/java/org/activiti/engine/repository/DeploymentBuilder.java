/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.engine.repository;

import java.io.InputStream;
import java.util.Date;
import java.util.zip.ZipInputStream;

import org.activiti.bpmn.model.BpmnModel;

/**
 * Builder for creating new deployments.
 * 
 * A builder instance can be obtained through {@link org.activiti.engine.RepositoryService#createDeployment()}.
 * 
 * Multiple resources can be added to one deployment before calling the {@link #deploy()}
 * operation.
 * 
 * After deploying, no more changes can be made to the returned deployment
 * and the builder instance can be disposed.
 *
 * @author Tom Baeyens
 * @author Joram Barrez
 */
public interface DeploymentBuilder {
	 public static final int Deploy_policy_default = 0;
	  public static final int Deploy_policy_upgrade = 1;
	  public static final int Deploy_policy_delete = 2;
  DeploymentBuilder addInputStream(String resourceName, InputStream inputStream);
  DeploymentBuilder addClasspathResource(String resource);
  DeploymentBuilder addString(String resourceName, String text);
  DeploymentBuilder addZipInputStream(ZipInputStream zipInputStream);
  DeploymentBuilder addBpmnModel(String resourceName, BpmnModel bpmnModel);
  
  /**
   * Gives the deployment the given name.
   */
  DeploymentBuilder name(String name);
  
  /**
   * Gives the deployment the given category.
   */
  DeploymentBuilder category(String category);
  
  /**
   * If set, this deployment will be compared to any previous deployment.
   * This means that every (non-generated) resource will be compared with the
   * provided resources of this deployment.
   */
  DeploymentBuilder enableDuplicateFiltering();
  
  /**
   * Sets the date on which the process definitions contained in this deployment
   * will be activated. This means that all process definitions will be deployed
   * as usual, but they will be suspended from the start until the given activation date.
   */
  DeploymentBuilder activateProcessDefinitionsOn(Date date);

  /**
   * Deploys all provided sources to the Activiti engine.
   */
  Deployment deploy();
  /**
   * Deploys all provided sources to the Activiti engine.
   */
  Deployment deploy(int deploypolicy);
  int getDeployPolicy();
  
}
