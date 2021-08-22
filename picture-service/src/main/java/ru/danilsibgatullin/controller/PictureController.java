package ru.danilsibgatullin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.danilsibgatullin.interfaces.PictureServiceInterface;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/picture")
public class PictureController {
    private final PictureServiceInterface pictureService;

    @Autowired
    public PictureController(PictureServiceInterface pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/{pictureId}")
    public void downloadProductPicture(@PathVariable("pictureId") Long pictureId,
                                       HttpServletResponse response) throws IOException {
        Optional<String> opt = pictureService.getPictureContentTypeById(pictureId);
        if (opt.isPresent()) {
            response.setContentType(opt.get());
            response.getOutputStream().write(pictureService.getPictureDataById(pictureId).get());
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @PostMapping("del/{id}")
    public String delPicture(@PathVariable("id")Long id,
                             @RequestParam("productId") Optional<Long> productId){
        pictureService.deletePicture(id);
        return "redirect:/product/"+productId.get();
    }
}

