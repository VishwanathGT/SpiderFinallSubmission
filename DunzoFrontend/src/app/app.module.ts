import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ShowProductsComponent } from './show-products/show-products.component';
import { Ng2SmartTableModule } from 'ng2-smart-table';
// import { NgxDropzoneModule } from 'ngx-dropzone';
import { ImageUploaderComponent } from './image-uploader/image-uploader.component';
import { GoogleChartsModule } from 'angular-google-charts';
import { RecommendProductComponent } from './recommend-product/recommend-product.component';
import { HttpClientModule } from '@angular/common/http';
import { DropzoneModule } from 'ngx-dropzone-wrapper';
import { ToastrModule } from 'ngx-toastr';


@NgModule({
  declarations: [
    AppComponent,
    ShowProductsComponent,
    ImageUploaderComponent,
    RecommendProductComponent
  ],
  imports: [
    BrowserModule,
    Ng2SmartTableModule,
    AppRoutingModule,
    // NgxDropzoneModule,
    GoogleChartsModule.forRoot(),
    HttpClientModule,
    DropzoneModule,
    ToastrModule.forRoot()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
