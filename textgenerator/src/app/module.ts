import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // Import this
import { TextGeneratorComponent } from './textgenerator/textgenerator.component';

@NgModule({
  declarations: [
    
    TextGeneratorComponent
  ],
  imports: [
    BrowserModule,
    FormsModule // Add FormsModule here
  ],
  providers: [],
  bootstrap: []
})
export class AppModule { }
