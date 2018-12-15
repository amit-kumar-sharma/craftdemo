/**
 * 
 */
package org.craft.demo.web.graphvisualization.controller;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.craft.demo.analyticalstore.Vertex;
import org.craft.demo.dataimport.model.CustomerDataModel;
import org.craft.demo.visualization.VisualizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author asharma
 *
 */
@Controller
public class GraphVisualizationController {
	
	@Autowired
	private VisualizationService<Map<Vertex, Set<Vertex>>> visualizationService;
	
	@GetMapping("/visualization")
    public String viewHome(Model model) {
		final Map<Vertex, Set<Vertex>> sameAsVisualizationData = visualizationService.getVisualizationData();
		final Map<CustomerDataModel, Set<CustomerDataModel>> visualizationData = new LinkedHashMap<>();
		if(sameAsVisualizationData!=null) {
			sameAsVisualizationData.entrySet().forEach(entry -> {
				if(entry.getValue()==null) {
					visualizationData.computeIfAbsent((CustomerDataModel)entry.getKey().getData(), k-> new HashSet<>());
				} else {
					visualizationData.computeIfAbsent((CustomerDataModel)entry.getKey().getData(), k-> new HashSet<>());
					entry.getValue().forEach(vertex-> {
						visualizationData.get((CustomerDataModel)entry.getKey().getData()).add((CustomerDataModel)vertex.getData());
					});
				}
			});
		}
		model.addAttribute("visualizationData", visualizationData);
        return "result";
    }
}
