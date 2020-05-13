package fct.unl.pt.instagramplus.Controllers.FilesTODO;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = FilesControllerInterface.FILES_BASE_URL)
public interface FilesControllerInterface {

    String FILES_BASE_URL = "/files";

    String DOWNLOAD = "/download";
    String UPLOAD = "/upload";

    //TODO


}
