package org.ascolto.onlus.geocrowd19.android.ui.home.family.details.edit

import android.os.Bundle
import androidx.lifecycle.Observer
import com.bendingspoons.base.extensions.setLightStatusBarFullscreen
import kotlinx.android.synthetic.main.user_edit_name_activity.*
import org.ascolto.onlus.geocrowd19.android.AscoltoActivity
import org.ascolto.onlus.geocrowd19.android.R
import org.ascolto.onlus.geocrowd19.android.loading
import org.ascolto.onlus.geocrowd19.android.models.Nickname
import org.ascolto.onlus.geocrowd19.android.models.NicknameType
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class EditNameActivity : AscoltoActivity() {
    private lateinit var viewModel: EditDetailsViewModel
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_edit_name_activity)
        setLightStatusBarFullscreen(resources.getColor(android.R.color.transparent))
        userId = intent?.extras?.getString("userId")!!
        viewModel = getViewModel { parametersOf(userId)}

        viewModel.navigateBack.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                finish()
            }
        })

        viewModel.user.observe(this, Observer {
            // TODO fill current age group
        })

        viewModel.loading.observe(this, Observer {
            loading(it)
        })

        back.setOnClickListener { finish() }

        update.setOnClickListener {
            // TODO save new age group
            val nickname = Nickname(NicknameType.OTHER, "Marco")

            val user = viewModel.user()
            user?.let {
                viewModel.updateUser(user.copy(nickname = nickname))
            }
        }
    }
}
