import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-default-register-layout',
  standalone: true,
  imports: [],
  templateUrl: './default-register-layout.component.html',
  styleUrl: './default-register-layout.component.css'
})
export class DefaultRegisterLayoutComponent {
// propriedades de entrada (Inputs) para o componente
  @Input() title: string = '';
}
