import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-default-login-layout',
  standalone: true,
  imports: [],
  templateUrl: './default-login-layout.component.html',
  styleUrl: './default-login-layout.component.css'
})
export class DefaultLoginLayoutComponent {

  @Input() title: String = "";
  @Input() subtitle: String = "";
  @Input() primaryButton: String = "";
  @Input() secondaryButton: String = "";
}
