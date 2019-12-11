import UIKit

import client_mpp

class ViewController: UIViewController {
    let api = ApplicationApi()

    @IBOutlet weak var aboutText: UITextView!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        api.about { (description) in
            self.aboutText.text = description.name
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
