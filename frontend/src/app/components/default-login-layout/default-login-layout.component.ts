import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-default-login-layout',
  standalone: true,
  imports: [],
  templateUrl: './default-login-layout.component.html',
  styleUrl: './default-login-layout.component.css'
})
export class DefaultLoginLayoutComponent {

  // propriedades de entrada (Inputs e Outputs) para o componente
  @Input() title: String = "";
  @Input() subtitle: String = "";
  @Input() primaryButton: String = "";
  @Input() secondaryButton: String = "";

  @Output("submit") onSubmit = new EventEmitter();

  // função para emitir o evento de submit
  submit(){
    this.onSubmit.emit();
  }
}
