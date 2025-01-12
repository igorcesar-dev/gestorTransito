import { Component, forwardRef, Input } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR, ReactiveFormsModule } from '@angular/forms';

// tipos de input permitidos
type InputTypes = "text" | "password" | "datetime-local" | "email" | "number" | "search" | "tel" | "text" | "url" | "time";

@Component({
  selector: 'app-primary-input',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  providers: [
    {
      // registro do componente como o fornecedor de NG_VALUE_ACCESSOR para permitir integração com o formulário
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => PrimaryInputComponent),
      multi: true
    }
  ],
  templateUrl: './primary-input.component.html',
  styleUrl: './primary-input.component.css'
})
export class PrimaryInputComponent implements ControlValueAccessor {
  // definindo as propriedades de entrada do componente
  @Input() type: InputTypes = "text";
  @Input() label: string = "";
  @Input() placeholder: string = "";
  @Input() inputName: string = "";

  // variáveis internas para controlar o valor e as funções do ControlValueAccessor
  value: string = ''
  onChange: any = () => { }
  onTouched: any = () => { }

  // função chamada quando o valor do input muda
  onInput(event: Event) {
    const value = (event.target as HTMLInputElement).value
    this.onChange(value)
  }

  // atualiza o valor do input
  writeValue(value: any): void {
    this.value = value
  }

  // registra a função de alteração do valor
  registerOnChange(fn: any): void {
    this.onChange = fn
  }

  // registra a função de toque no input
  registerOnTouched(fn: any): void {
    this.onTouched = fn
  }
}
