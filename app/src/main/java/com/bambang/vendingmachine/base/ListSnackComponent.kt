

import com.bambang.vendingmachine.viewmodel.ListSnackViewModel
import dagger.Component

@Component(modules = [ListSnackModule::class])
interface ListSnackComponent {
    fun inject(listSnackViewModel: ListSnackViewModel)


    @Component.Builder
    interface Builder {
        fun module(module: ListSnackModule): Builder
        fun build(): ListSnackComponent
    }
}