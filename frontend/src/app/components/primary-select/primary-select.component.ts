import { Component, forwardRef, Input } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-primary-select',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './primary-select.component.html',
  styleUrls: ['./primary-select.component.css'],
  providers: [
    {
      // registro do componente como o fornecedor de NG_VALUE_ACCESSOR para permitir integração com o formulário
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => PrimarySelectComponent),
      multi: true,
    },
  ],
})
export class PrimarySelectComponent implements ControlValueAccessor {
  // definindo as propriedades de entrada do componente
  @Input() label: string = ''; 
  @Input() inputName: string = ''; 
  @Input() options: { label: string; value: any }[] = []; 

  value: any;
  isDisabled: boolean = false;

  private onChange: (value: any) => void = () => {}; // função para notificar mudanças de valor
  private onTouched: () => void = () => {}; // função para notificar quando o campo é tocado

  // atualiza o valor do select
  writeValue(value: any): void {
    this.value = value;
  }

  // registra a função de alteração do valor
  registerOnChange(fn: (value: any) => void): void {
    this.onChange = fn;
  }

  // registra a função de toque no select
  registerOnTouched(fn: () => void): void {
    this.onTouched = fn;
  }

  // método para lidar com o estado desabilitado do select
  setDisabledState(isDisabled: boolean): void {
    this.isDisabled = isDisabled;
  }

  // função chamada quando o valor do select muda
  onSelectChange(event: Event): void {
    const value = (event.target as HTMLSelectElement).value;
    this.value = value;
    this.onChange(value); // notifica a mudança de valor
    this.onTouched(); // notifica que o campo foi tocado
  }
}
