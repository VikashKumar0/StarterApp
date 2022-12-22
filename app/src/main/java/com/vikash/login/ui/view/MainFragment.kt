package com.vikash.login.ui.viewmodel.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.vikash.login.databinding.FragmentMainBinding
import com.vikash.login.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signoutBtn.setOnClickListener { onSignout() }
        deleteUserBtn.setOnClickListener { onDelete() }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.signout.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "Sign out successfully." , Toast.LENGTH_LONG ).show()
            val action = MainFragmentDirections.actionGoToSignup()
            Navigation.findNavController(usernameTV).navigate(action)
        })
        viewModel.userDeleted.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "User deleted successfully." , Toast.LENGTH_LONG ).show()
            val action = MainFragmentDirections.actionGoToSignup()
            Navigation.findNavController(usernameTV).navigate(action)
        })
    }

    private fun onSignout() {
        viewModel.onSignout()
    }

    private fun onDelete() {
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle("Delete user")
                .setMessage("Do you really want to delete this user?")
                .setPositiveButton("Ok"){p0, p1-> viewModel.onDeleteUser()}
                .setNegativeButton("Cancel", null)
                .create()
                .show()
        }
    }
}
