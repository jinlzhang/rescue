package com.cjd.rescue.api.product;

import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.product.AddProjectParams;
import com.cjd.rescue.entity.product.Project;

public interface ProductApi {


    ReturnT list(Project resource);

    ReturnT add(AddProjectParams addProjectParams);

    ReturnT delete(Project project);

    ReturnT get(Project project);
}
