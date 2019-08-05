import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ShowProductsComponent } from './show-products/show-products.component';
import { ImageUploaderComponent } from './image-uploader/image-uploader.component';
import { RecommendProductComponent } from './recommend-product/recommend-product.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'show-products', pathMatch: 'full'
  },
  {
    path: 'show-products',
    component: ShowProductsComponent
  },
  {
    path: 'image-upload',
    component: ImageUploaderComponent
  },
  {
    path: 'product-recomendation',
    component: RecommendProductComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
