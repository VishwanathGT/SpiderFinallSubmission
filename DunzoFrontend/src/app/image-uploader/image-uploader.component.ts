import { Component, OnInit } from '@angular/core';
import { DropzoneConfigInterface } from 'ngx-dropzone-wrapper';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
// import { DropzoneDirective, DropzoneConfigInterface, DropzoneConfig } from 'ngx-dropzone';


@Component({
  selector: 'app-image-uploader',
  templateUrl: './image-uploader.component.html',
  styleUrls: ['./image-uploader.component.scss']
})
export class ImageUploaderComponent implements OnInit {

  fileData = null;
  uploadForm: FormGroup;
  DROPZONECONFIG: DropzoneConfigInterface = {

    url: 'http://10.105.24.243:8080/upload',
    acceptedFiles: 'images/*'
  };
  public files: any[];

  // contructor() { this.files = [];
  constructor(
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private toastr: ToastrService
  ) {
    this.files = [];
  }

  ngOnInit() {
    this.uploadForm = this.formBuilder.group({
      profile: ['']
    });
  }

  onFileChanged(event: any) {
    this.files = event.target.files[0];
    this.uploadForm.get('profile').setValue(this.files);
  }

  onUpload() {
    const formData = new FormData();
    formData.append('file', this.uploadForm.get('profile').value);

    const options = { content: formData };
    this.http.post('http://10.105.24.243:8080/upload', formData).subscribe((x) => {

      console.log('Sucess response');
      // alert('SUCCESS !!');
      this.toastr.success('Successfully Uploaded', 'Thank You !!', { progressBar: true });
    },
      (err) => {
        console.log(err);
      });
  }

}
