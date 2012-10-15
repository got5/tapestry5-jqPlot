//
// Copyright 2010 GOT5 (GO Tapestry 5)
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package org.got5.tapestry5.jquery.jqplot.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.services.ComponentClassTransformWorker;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.got5.tapestry5.jquery.jqplot.JqPlotSymbolConstants;
import org.got5.tapestry5.jquery.jqplot.services.javascript.JqPlotJavaScriptStack;



public class JqPlotModule
{
    public static void contributeJavaScriptStackSource(MappedConfiguration<String, JavaScriptStack> configuration)
    {
    	   configuration.addInstance(JqPlotJavaScriptStack.STACK_ID, JqPlotJavaScriptStack.class); 
    }

    
    public static void contributeComponentClassTransformWorker(OrderedConfiguration<ComponentClassTransformWorker> configuration)
    {
    	
    }

    public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration)
    {
        configuration.add(new LibraryMapping("jquery-jqplot", "org.got5.tapestry5.jquery.jqplot"));
    }

  
  
    
    public static void contributeFactoryDefaults(MappedConfiguration<String, String> configuration)
    {
       
        configuration.add(JqPlotSymbolConstants.JQUERY_JQPLOT_CORE_PATH, "classpath:org/got5/tapestry5/jquery/jqplot/jquery/jqplot_core");
        configuration.add(JqPlotSymbolConstants.JQUERY_JQPLOT_VERSION, "v1_0_0b2_r792"); // Fixing java class path issue (old version 1.0.0b2_r792 )
       
    }
    
    public static void contributeClasspathAssetAliasManager(MappedConfiguration<String, String> configuration)
    {
        configuration.add("tap-jquery-jqplot", "org/got5/tapestry5");
    }
    
   
    
   

}
