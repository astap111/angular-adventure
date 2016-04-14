var imageWithCropper;
var imageBlob;

var previewPhoto = function () {

    //Check File API support
    if (window.File && window.FileList && window.FileReader) {
        var filesInput = document.getElementById("file");
        var file = document.querySelector('input[type=file]').files[0];
        var pictureForCrop = document.getElementById("pictureForCrop");

        //Only pics
        if (!file.type.match('image')) return;

        var picReader = new FileReader();

        picReader.addEventListener("load", function (event) {
            var picFile = event.target;
            pictureForCrop.innerHTML = "<img id='previewPhoto' class='thumbnail' src='" + picFile.result + "'" +
                "title='" + picFile.name + "'/>";

            $image = $('#previewPhoto').cropper({
                aspectRatio: 3 / 4,
                modal: true,
                crop: function (e) {
                    // Output the result data for cropping image.
                    console.log(e.x);
                    console.log(e.y);
                    console.log(e.width);
                    console.log(e.height);
                    console.log(e.rotate);
                    console.log(e.scaleX);
                    console.log(e.scaleY);
                }
            });
        });

        //Read the image
        picReader.readAsDataURL(file);
    }
    else {
        console.log("Your browser does not support File API");
    }
};

function setResultToCanvas() {
    var croppedCanvas = $image.cropper('getCroppedCanvas');

    croppedCanvas.toBlob(function (blob) {
        $('#userPhoto').val(blob);
        imageBlob = blob;
    });

    $('#resultImage').html(croppedCanvas);
}

